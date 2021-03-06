package dao;

import dao.rowmapper.RowMapper;
import exceptions.ModelNotFoundException;
import model.Model;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static dao.ModifyingOperation.*;

public abstract class AbstractDao<E extends Model> implements Dao<E> {

    public static final int ID_ORDER_NUMBER = 0;
    public static final String UUID_HEADER_NAME = "UUID";

    @Override
    public E save(E model) {
        try (BufferedWriter writer = Files.newBufferedWriter(getDataFile().toPath(), StandardOpenOption.APPEND);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withSkipHeaderRecord())) {
            csvPrinter.printRecord(getModelData(model));
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return model;
    }

    @Override
    public E update(E model) {
        performModifyingOperation(model, UPDATE);
        return model;
    }

    @Override
    public void delete(E model) {
        performModifyingOperation(model, DELETE);
    }

    @Override
    public List<E> findAll() {
        File f = getDataFile();
        RowMapper<E> rowMapper = getRowMapper();
        try (CSVParser parser = new CSVParser(new FileReader(f), CSVFormat.DEFAULT.withHeader(getModelHeaders()))) {
            List<CSVRecord> list = parser.getRecords();
            return list.stream()
                    .map(rowMapper::mapRowFromRecord)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unexpected exception");
        }
    }

    @Override
    public E findById(UUID id) {
        File f = getDataFile();
        RowMapper<E> rowMapper = getRowMapper();
        try (CSVParser parser = new CSVParser(new FileReader(f), CSVFormat.DEFAULT.withHeader(getModelHeaders()))) {
            List<CSVRecord> list = parser.getRecords();
            return list.stream()
                    .filter(n -> n.get(UUID_HEADER_NAME).equalsIgnoreCase(id.toString()))
                    .map(rowMapper::mapRowFromRecord)
                    .findFirst()
                    .orElseThrow(ModelNotFoundException::new);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unexpected exception");
        }
    }

    private void performModifyingOperation(E model, ModifyingOperation operation) {
        File f = getDataFile();
        try (CSVParser parser = new CSVParser(new FileReader(f), CSVFormat.DEFAULT.withSkipHeaderRecord())) {
            List<CSVRecord> list = parser.getRecords();
            String edited = f.getAbsolutePath();
            f.delete();
            try (CSVPrinter printer = new CSVPrinter(new FileWriter(edited), CSVFormat.DEFAULT.withSkipHeaderRecord())) {
                iterateOverRecords(model, operation, list, printer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void iterateOverRecords(E model, ModifyingOperation operation, List<CSVRecord> list, CSVPrinter printer)
            throws Exception {
        for (CSVRecord record : list) {
            String[] s = recordToArray(record);
            if (operation.equals(DELETE) && isNeededRecord(model, s[ID_ORDER_NUMBER])) {
                continue;
            }
            if (operation.equals(UPDATE) && isNeededRecord(model, s[ID_ORDER_NUMBER])) {
                s = getModelData(model).toArray(new String[0]);
            }
            printRecord(printer, s);
        }
    }

    protected E findSingleModelByPredicate(Predicate<CSVRecord> conditions) {
        File f = getDataFile();
        RowMapper<E> rowMapper = getRowMapper();
        try (CSVParser parser = new CSVParser(new FileReader(f), CSVFormat.DEFAULT.withHeader(getModelHeaders()))) {
            List<CSVRecord> list = parser.getRecords();
            return list.stream()
                    .filter(conditions)
                    .map(rowMapper::mapRowFromRecord)
                    .findFirst()
                    .orElseThrow(ModelNotFoundException::new);
        } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Unexpected exception");
        }
    }

    protected List<E> findMultipleModelsByPredicate(Predicate<CSVRecord> conditions) {
        File f = getDataFile();
        RowMapper<E> rowMapper = getRowMapper();
        try (CSVParser parser = new CSVParser(new FileReader(f), CSVFormat.DEFAULT.withHeader(getModelHeaders()))) {
            List<CSVRecord> list = parser.getRecords();
            return list.stream()
                    .filter(conditions)
                    .map(rowMapper::mapRowFromRecord)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unexpected exception");
        }
    }

    private boolean isNeededRecord(E model, String s2) {
        return s2.equalsIgnoreCase(model.getId().toString());
    }


    public static String[] recordToArray(CSVRecord rec) {
        String[] arr = new String[rec.size()];
        int i = 0;
        for (String str : rec) {
            arr[i++] = str;
        }
        return arr;
    }


    public static void printRecord(CSVPrinter printer, String[] s) throws Exception {
        for (String val : s) {
            printer.print(val != null ? String.valueOf(val) : "");
        }
        printer.println();
    }

    protected abstract String[] getModelHeaders();

    protected abstract File getDataFile();

    protected abstract List<String> getModelData(E model);

    protected abstract RowMapper<E> getRowMapper();
}

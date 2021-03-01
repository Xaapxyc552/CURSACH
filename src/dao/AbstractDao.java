package dao;

import model.Model;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

public abstract class AbstractDao<E extends Model> implements Dao<E> {

    public static final int ID_ORDER_NUMBER = 0;

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
        doSomethingWithMode(model, Operation.UPDATE);
        return model;
    }

    @Override
    public void delete(E model) {
        doSomethingWithMode(model, Operation.DELETE);
    }

    private void doSomethingWithMode(E model, Operation operation) {
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

    private void iterateOverRecords(E model, Operation operation, List<CSVRecord> list, CSVPrinter printer) throws Exception {
        for (CSVRecord record : list) {
            String[] s = toArray(record);
            if (operation.equals(Operation.DELETE) && isNeededRecord(model, s[ID_ORDER_NUMBER])) {
                continue;
            }
            if (operation.equals(Operation.UPDATE) && isNeededRecord(model, s[ID_ORDER_NUMBER])) {
                s = getModelData(model).toArray(new String[0]);
            }
            print(printer, s);
        }
    }

    @Override
    public List<E> findAll() {
        return null;
    }

    @Override
    public E findById(long id) {
        return null;
    }

    private boolean isNeededRecord(E model, String s2) {
        return s2.equalsIgnoreCase(model.getId().toString());
    }


    public static String[] toArray(CSVRecord rec) {
        String[] arr = new String[rec.size()];
        int i = 0;
        for (String str : rec) {
            arr[i++] = str;
        }
        return arr;
    }


    public static void print(CSVPrinter printer, String[] s) throws Exception {
        for (String val : s) {
            printer.print(val != null ? String.valueOf(val) : "");
        }
        printer.println();
    }

    protected abstract String[] getModelHeaders();

    protected abstract File getDataFile();

    protected abstract List<String> getModelData(E model);
}

package dao.rowmapper;

import model.Model;
import org.apache.commons.csv.CSVRecord;

public interface RowMapper<E extends Model> {
    public E mapRowFromRecord(CSVRecord record);
}

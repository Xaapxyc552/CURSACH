package dao;

import model.test.Test;

import java.util.List;

public interface TestDao extends Dao<Test>{
    public Test findTestByName(String name);

}

import dao.AnswerDao;
import dao.DaoFactory;
import dao.DataStorageInitializer;
import dao.QuestionDao;
import front.frame.LoginFrame;
import model.test.Answer;
import model.test.Question;
import model.test.Test;
import model.test.Topic;
import model.user.Role;
import model.user.User;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestingSystemApplication {

    public static void main(String[] args) {
        initializeSystemIfNeeded();

        LoginFrame dialog = new LoginFrame();
        dialog.pack();
        dialog.setVisible(true);
    }

    private static void initializeSystemIfNeeded() {
        try {
            if (!DataStorageInitializer.checkIsStorageInitialized()) {
                DataStorageInitializer.clearData();
                DataStorageInitializer.initializeDataStorage();
                DataStorageInitializer.fillSampleData();
            }
        } catch (Exception e) {
            DataStorageInitializer.clearData();
            throw new IllegalStateException("Exception during system initialization", e);
        }
    }


}

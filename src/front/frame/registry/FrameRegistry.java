package front.frame.registry;

import front.frame.LoginFrame;
import front.frame.TeacherMainPanel;
import front.frame.TestsListFrame;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class FrameRegistry {
    //TODO подключить к проге

    private static FrameRegistry registry;
    private final Map<Class<? extends JFrame>, JFrame> frameMap = new HashMap<>();

    public static FrameRegistry getInstance() {
        if (registry == null) {
            registry = new FrameRegistry();
        }
        return registry;
    }

    public FrameRegistry() {
        frameMap.put(LoginFrame.class, new TestsListFrame());
        frameMap.put(TeacherMainPanel.class, new TestsListFrame());
        frameMap.put(TestsListFrame.class, new TestsListFrame());
    }

    public JFrame getFrame(Class<? extends JFrame> frameClass) {
        return frameMap.get(frameClass);
    }

}

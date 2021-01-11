package BreakTimer;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

import java.util.Random;

public class Tips extends AnAction {
    private String[] messages;
    private int counter;

    public Tips() {
        super("Tips!");
        messages = new String[4];
        messages[0] = "If it feels wrong, don't do it!";
        messages[1] = "Don't talk bad about yourself, you're doing great :)";
        messages[2] = "Don't be afraid to ask for help, it'll help you learn!";
        messages[3] = "Movement reduces the risk of illness and injury, while increasing awareness, attention span and " +
                "endorphins, so make sure to exercise!";

        counter = new Random().nextInt(messages.length);

    }

    public void actionPerformed(AnActionEvent event) {
        Project project = event.getProject();

        int val = 0;
        while (val ==0) {
            val = Messages.showOkCancelDialog(this.messages[this.counter % messages.length], "Here's An Idea", "Next",
                    "Cancel", Messages.getInformationIcon());
            System.out.println(val);
            this.counter++;
        }
    }
}
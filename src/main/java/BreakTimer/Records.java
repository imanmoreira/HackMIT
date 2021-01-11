package BreakTimer;

import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.NonEmptyInputValidator;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Records extends AnAction implements ProjectComponent {
    NotificationGroup notificationGroup = new NotificationGroup("display id",
            NotificationDisplayType.BALLOON,
            true, null, null);
    private int[] counter;
    //private Timer timer;
    private int interval;

    public Records() {
        this.counter = new int[2];
        this.interval = 1;
    }
/*
    public void setTimer() {
        this.timer = new Timer(interval * 6000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recordFeelings();
            }
        });
    }

    @Override
    public void projectOpened() {
        this.setTimer();
        this.timer.start();
    }

 */
    public void actionPerformed(AnActionEvent e) {
        recordFeelings();
    }

    public void recordFeelings() {
        String[] choices = new String[5];
        choices[0] = "happy";
        choices[1] = "neutral";
        choices[2] = "sad";
        choices[3] = "angry";
        choices[4] = "dying";
        String feelingsATM = Messages.showEditableChooseDialog(
                "Select the emotion that best fits how you're feeling now",
                "How do you feel?",
                Messages.getQuestionIcon(),
                choices,
                choices[0],
                new NonEmptyInputValidator());

        if (feelingsATM != null && (feelingsATM.equals(choices[0]) || feelingsATM.equals(choices[1]))) {
            counter[0] += 1;
        }
        if (feelingsATM != null &&( feelingsATM.equals(choices[2]) || feelingsATM.equals(choices[3])
                || feelingsATM.equals(choices[4]))) {
            counter[1] += 1;
        }

        if (counter[0] >= 3) {
            Messages.showMessageDialog("You're doing well, my child. Keep up the good work. :>",
                    "Jesus got nothing on you", Messages.getInformationIcon());
        }
        if (counter[1] >= 3) {
            Messages.showMessageDialog("I think it's time for you to take a break :')",
                    "Seek help", Messages.getWarningIcon());
        }
        counter[0] = counter[0] % 3;
        counter[1] = counter[1] % 3;
    }
}

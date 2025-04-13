package ru.stqua.mantis.manager;

import jakarta.mail.*;
import ru.stqua.mantis.model.MailMessage;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

public class MailHelper extends HelperBase{
    public MailHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<MailMessage> receive(String username, String password, Duration duration)
    {
        var start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < duration.toMillis()) {
            try {
                var inbox = getInbox(username, password);
                inbox.open(Folder.READ_ONLY);
                var messages = inbox.getMessages();
                var result = Arrays.stream(messages).map(m -> {
                            try {
                                return new MailMessage()
                                        .withFrom(m.getFrom()[0].toString())
                                        .withContent((String) m.getContent());
                            } catch (MessagingException | IOException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .toList();
                inbox.close();
                inbox.getStore().close();
                if (!result.isEmpty()) {return result;}
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("Почта не пришла");
    }

    private static Folder getInbox(String username, String password){

        try {
            var session = Session.getInstance(new Properties());
            Store store =  session.getStore("pop3");
            store.connect("localhost", username, password);
            var inbox = store.getFolder("INBOX");
            return inbox;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public void drain(String username, String password)
    {

        try {
            var inbox = getInbox(username,password);
            inbox.open(Folder.READ_WRITE);
            Arrays.stream(inbox.getMessages()).forEach(m-> {
                try {
                    m.setFlag(Flags.Flag.DELETED, true);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });
            inbox.close();
            inbox.getStore().close();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
    public String extractUrlFromLastMail(String email, String password)
    {
        {
            var messages = manager.mail().receive(email,password, Duration.ofSeconds(10));
            var text = messages.get(0).content();
            var pattern = Pattern.compile("http://\\S*");
            var matcher = pattern.matcher(text);
            if (matcher.find())
            {
                return text.substring(matcher.start(),matcher.end());
            }
            throw new RuntimeException("didnt got any"); // возможно сюда не попадем т.к. на receive упадём
        }
    }
}

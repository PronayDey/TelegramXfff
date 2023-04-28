package org.example;

import java.util.Random;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyTelegramBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {

            String messageText = update.getMessage().getText();
            if (messageText.equals("/otp")) {
                String otp = generateOTP();
                SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
                message.setChatId(update.getMessage().getChatId());
                message.setText("Your otp is " + otp);
                System.out.println("Your otp is " + otp);

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }


    private String generateOTP() {
        Random random = new Random();
        // Generate a 6-digit random number
        int otp = 100000 + random.nextInt(900000);
        // Wait for 5 seconds to simulate time-based OTP generation

        return String.valueOf(otp);
    }

    @Override
    public String getBotToken() {
        return "6289412503:AAELVaHDO8qITAIzcyihFsvOda7MWNawg3A";
    }

    @Override
    public String getBotUsername() {
        return "ProntyBot";
    }
}


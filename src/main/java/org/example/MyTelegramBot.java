package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;




import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;



public class MyTelegramBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {


            Message message = update.getMessage();
            if (message.hasText()) {
                String messageText = update.getMessage().getText();
                long chatId = update.getMessage().getChatId();
                String username = update.getMessage().getFrom().getFirstName();
                Date date = new Date();
                Timestamp ts = new Timestamp(date.getTime());

                Manager manager = new Manager();
                SendMessage sendMessage = new SendMessage();
                String response = manager.getResponse(messageText);
                //System.out .println(response);
                sendMessage.setText(response);
                sendMessage.setChatId(message.getChatId());
                try {
                    // Send the message
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

                String text = message.getText();
                if (text.equals("/start")) {


                    sendMessage.setText("User Entered chatbot");

                    List<String> buttonLabels = new ArrayList<>();
                    buttonLabels.add("reservation");
                    buttonLabels.add("Menu");
                    buttonLabels.add("order online");
                    buttonLabels.add("call us");
                    HashMap<String,String> map= new HashMap<>();
                    map.put( "reservation","reservation");
                    map.put("Menu","Menu");
                    map.put("order online","order online");
                    map.put("call us","call us");
                   // System.out.println(map);
                    createInlineKeyboard( chatId,  sendMessage, buttonLabels,  map);


                }
                else if (text.matches("[0-9]+")) {

                    sendMessage.setText("Please Enter your Name and phone for contacting you after reservation");


                } else if (text.matches("[0-9 -:]+")) {

                    sendMessage.setText("Please Enter the no of persons comming to the party");


                } else if (text.matches("[A-Z a-z]")) {


                    sendMessage.setText(response);

                }

                 else   {

                    sendMessage.setText("Great your reservation is done and you will be getting a call shortly from the concerned team");


                }try {
                    // Send the message
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

                manager.saveChat(chatId, messageText, username, ts);



            }
        }

         else if (update.hasCallbackQuery()) {
            Message message = update.getCallbackQuery().getMessage();

            CallbackQuery callbackQuery = update.getCallbackQuery();
            String data = callbackQuery.getData();

            Date date = new Date();
            Timestamp ts = new Timestamp(date.getTime());
            Manager manager = new Manager();
            SendMessage sendMessage = new SendMessage();
//            SendMessage SendMessage = new SendMessage();
            String response=manager.getResponse(data);
            //System.out.println(response);
          sendMessage.setChatId(message.getChatId());
           sendMessage.setText(response);
            try {

                execute(sendMessage);

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

         String msg= manager.getUserMessage(response);
//
           System.out.println(msg);
                if (data.equals("call us")) {
                    sendMessage.setText("Press below button to call us 24*7");


                } else if
                (data.equals("order online")) {
                    //SendMessage sendMessage1 = new SendMessage();
                    sendMessage.setText("Menu");
                    sendMessage.setChatId(message.getChatId());

                    List<String> buttonLabels = new ArrayList<>();
                    buttonLabels.add("Bevarages");
                    buttonLabels.add("Staters");
                    buttonLabels.add("Deserts");
                    buttonLabels.add("Main Course");
                    HashMap<String, String> map = new HashMap<>();
                    map.put("Bevarages", "Bevarages");
                    map.put("Staters", "Staters");
                    map.put("Deserts", "Deserts");
                    map.put("Main Course", "Main Course");
                    // System.out.println(map);
                    createInlineKeyboard(message.getChatId(), sendMessage, buttonLabels, map);


                } else if
                (data.equals("Menu")) {

                    //SendMessage sendMessage = new SendMessage();
                    sendMessage.setText("menu");
                    sendMessage.setChatId(message.getChatId());
                    List<String> buttonLabels = new ArrayList<>();
                    buttonLabels.add("beverages");
                    buttonLabels.add("staters");
                    buttonLabels.add("deserts");
                    buttonLabels.add("main course");
                    HashMap<String, String> map = new HashMap<>();
                    map.put("beverages", "beverages");
                    map.put("staters", "staters");
                    map.put("deserts", "deserts");
                    map.put("main course", "main course");
                    // System.out.println(map);
                    createInlineKeyboard(message.getChatId(), sendMessage, buttonLabels, map);
//

                }
                else if (data.equals("beverages")) {

                    sendMessage.setText("Coffe(Rs 50)" + '\n' +
                            "Tea (Rs 20)" + '\n' + "Mojito (Rs 70)" + '\n' + "Juice (Rs 40)" + '\n' + "Smoothie(Rs 80)");
                } else if (data.equals("staters")) {
                    sendMessage.setText("Paneer Tikka(Rs 120)" + '\n' +
                            "Mushroom Tandori(Rs 140)" + '\n' + "chaap(Rs 140)" + '\n' + "Crispy (Rs 160)");

                }
                else if (data.equals("deserts")) {
                    sendMessage.setText("Gulab jamun(Rs 50)" + '\n' +
                            "Rasgulla (Rs 40)" + '\n' + "Ice cream (Rs 50)" + '\n' + "Rass Malavi(Rs 80)");

                }
                else if (data.equals("main course")) {
                    sendMessage.setText("Dal Makhni(Rs 180)" + '\n' +
                            "Rajma(Rs 140)" + '\n' + "Paneer Masala (Rs 200)" + '\n' + "Pindi Channa(Rs 130)" + '\n' + "Roti (Rs 20)" + '\n' + "Naan (Rs 40)");
                }
                else if
                (data.equals("reservation")) {
                    sendMessage.setText("Please Enter the date and time you want to reserve a table");
                }

            else if (data.equals("Bevarages")) {
               // SendMessage sendMessage1 = new SendMessage();
                sendMessage.setText("Bevarages");
                sendMessage.setChatId(message.getChatId());

                List<String> buttonLabels = new ArrayList<>();
                buttonLabels.add("Coffe(Rs 50)");
                buttonLabels.add("Tea (Rs 20)");
                buttonLabels.add("Mojito (Rs 70)");
                buttonLabels.add("Juice (Rs 40)");
                buttonLabels.add("Smoothie(Rs 80)");
                HashMap<String,String> map= new HashMap<>();
                map.put( "Coffe(Rs 50)","c");
                map.put("Tea (Rs 20)","t");
                map.put("Mojito (Rs 70)","m");
                map.put("Juice (Rs 40)","j");
                map.put("Smoothie(Rs 80)","s");
                // System.out.println(map);
                createInlineKeyboard( message.getChatId(),  sendMessage, buttonLabels,  map);


            }
            else if (data.equals("Staters")) {
               // SendMessage sendMessage1 = new SendMessage();
                sendMessage.setText("Starters");
                sendMessage.setChatId(message.getChatId());
                List<String> buttonLabels = new ArrayList<>();
                buttonLabels.add("Paneer Tikka(Rs 120)");
                buttonLabels.add("Mushroom Tandori(Rs 140)");
                buttonLabels.add("chaap(Rs 140)");
                buttonLabels.add("Crispy (Rs 160)");

                HashMap<String,String> map= new HashMap<>();
                map.put( "Paneer Tikka(Rs 120)","p");
                map.put("Mushroom Tandori(Rs 140)","mu");
                map.put("chaap(Rs 140)","ch");
                map.put("Crispy (Rs 160)","cr");

                // System.out.println(map);
                createInlineKeyboard( message.getChatId(),  sendMessage, buttonLabels,  map);


            }
            else if (data.equals("Deserts")) {


               // SendMessage sendMessage1 = new SendMessage();
                sendMessage.setText("Deserts");
                sendMessage.setChatId(message.getChatId());

                List<String> buttonLabels = new ArrayList<>();
                buttonLabels.add("Gulab jamun(Rs 50)");
                buttonLabels.add("Rasgulla (Rs 40)");
                buttonLabels.add("Ice cream (Rs 50)");
                buttonLabels.add("Rass Malavi(Rs 80)");

                HashMap<String,String> map= new HashMap<>();
                map.put( "Gulab jamun(Rs 50)","g");
                map.put("Rasgulla (Rs 40)","r");
                map.put("Ice cream (Rs 50)","i");
                map.put("Rass Malavi(Rs 80)","rm");

                // System.out.println(map);
                createInlineKeyboard( message.getChatId(),  sendMessage, buttonLabels,  map);

//

            }
            else if (data.equals("Main Course")) {
                //SendMessage sendMessage1 = new SendMessage();
                sendMessage.setText("Main Course");
                sendMessage.setChatId(message.getChatId());

                List<String> buttonLabels = new ArrayList<>();
                buttonLabels.add("Dal Makhni(Rs 180)");
                buttonLabels.add("Rajma(Rs 140)");
                buttonLabels.add("Paneer Masala (Rs 200))");
                buttonLabels.add("Pindi Channa(Rs 130))");
                buttonLabels.add("Roti (Rs 20)");
                buttonLabels.add("Naan (Rs 40)");

                HashMap<String,String> map= new HashMap<>();
                map.put( "Dal Makhni(Rs 180)","d");
                map.put("Rajma(Rs 140)","r");
                map.put("Paneer Masala (Rs 200)","pm");
                map.put("Pindi Channa(Rs 130)","pc");
                map.put("Roti (Rs 20)","ro");
                map.put("Naan (Rs 40)","n");

                // System.out.println(map);
                createInlineKeyboard( message.getChatId(),  sendMessage, buttonLabels,  map);




            }
            else if
                (data.equals("c") || data.equals("t") || data.equals("m") || data.equals("s") || data.equals("j") || data.equals("p") ||
                    data.equals("mu") || data.equals("ch") || data.equals("cr") || data.equals("d") || data.equals("ro") || data.equals("pm") || data.equals("pc")
                    || data.equals("n") || data.equals("g") || data.equals("r") || data.equals("i") || data.equals("rm")
            ) {
               // SendMessage sendMessage1 = new SendMessage();
                sendMessage.setText("Cart");
                sendMessage.setChatId(message.getChatId());
                List<String> buttonLabels = new ArrayList<>();
                buttonLabels.add("Add to cart ?");


                HashMap<String,String> map= new HashMap<>();
                map.put( "Add to cart ?","atc");


                // System.out.println(map);
                createInlineKeyboard( message.getChatId(),  sendMessage, buttonLabels,  map);

//
            } else if
            (data.equals("atc")) {
               // SendMessage sendMessage1 = new SendMessage();
                sendMessage.setText("Add to cart?");
                sendMessage.setChatId(message.getChatId());
                List<String> buttonLabels = new ArrayList<>();
                buttonLabels.add("Yes");
                buttonLabels.add("No");

                HashMap<String,String> map= new HashMap<>();
                map.put( "Yes","y");
                map.put("No","N");

                // System.out.println(map);
                createInlineKeyboard( message.getChatId(),  sendMessage, buttonLabels,  map);

//

            }
            else if (data.equals("y")) {
                sendMessage.setText("The total amount is -");

            } else if (data.equals("N")) {
                sendMessage.setText("user will have to choose anything from cart");
            }

            try {

                execute(sendMessage);

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            manager.saveChat(message.getChatId(), data, message.getFrom().getUserName(), ts);
        }

    }
    public void createInlineKeyboard(long chatId, SendMessage sendMessage,List<String> buttonLabels, HashMap< String,String> map) {
        InlineKeyboardMarkup inlinekeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();
          int count=buttonLabels.size();
          for(int i=0;i<count;i++){
              InlineKeyboardButton inlineKeyboardButton= new InlineKeyboardButton();
              buttons.add(inlineKeyboardButton);
          }
          for(int j=0;j<buttons.size();j++){
              buttons.get(j).setText(buttonLabels.get(j));
              buttons.get(j).setCallbackData(map.get(buttonLabels.get(j)));
          }
        for(int i=0;i<count;i++){
            List<InlineKeyboardButton> button = new ArrayList<>();
            keyboard.add(button);
        }
        for (int j=0;j<keyboard.size();j++){
            keyboard.get(j).add(buttons.get(j));
        }
        List<List<InlineKeyboardButton>> rc = new ArrayList<>();
        for (int j=0;j<keyboard.size();j++){

            rc.add(keyboard.get(j));
        }
        inlinekeyboardMarkup.setKeyboard(rc);
      sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(inlinekeyboardMarkup);



    }



    @Override
        public String getBotToken () {
            return "6289412503:AAELVaHDO8qITAIzcyihFsvOda7MWNawg3A";
        }

        @Override
        public String getBotUsername () {
            return "ProntyBot";
        }


}


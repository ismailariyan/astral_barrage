import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class chatActivity extends AppCompatActivity {
    private ListView chatListView;
    private EditText userInputEditText;
    private Button sendButton;
    private List<Message> messages;
    private MessageAdapter messageAdapter; // Create an adapter to display messages

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Initialize UI elements
        chatListView = findViewById(R.id.chatListView);
        userInputEditText = findViewById(R.id.userInputEditText);
        sendButton = findViewById(R.id.sendButton);

        // Initialize the list of messages
        messages = new ArrayList<>();

        // Initialize the message adapter
        messageAdapter = new MessageAdapter(this, messages);

        // Set the adapter to the ListView
        chatListView.setAdapter(messageAdapter);

        // Set a click listener for the send button
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        // Get user input message
        String userMessage = userInputEditText.getText().toString();
    
        // Create a new message and add it to the list
        Message userMessageObj = new Message(userMessage, true);
        messages.add(userMessageObj);
    
        messageAdapter.notifyDataSetChanged();
    
        // Clear the user input field
        userInputEditText.setText("");
    
        String chatbotResponse = getChatbotResponseFromAPI(userMessage);
    
        Message chatbotMessageObj = new Message(chatbotResponse, false);
        messages.add(chatbotMessageObj);
    
        messageAdapter.notifyDataSetChanged();
    
    
        chatListView.smoothScrollToPosition(messages.size() - 1);
    }
    
    private String getChatbotResponseFromAPI(String userMessage) {
    
        return  userMessage;
    }
    
}

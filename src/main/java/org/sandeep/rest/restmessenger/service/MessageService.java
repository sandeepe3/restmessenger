package org.sandeep.rest.restmessenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.sandeep.rest.restmessenger.database.DatabaseClass;
import org.sandeep.rest.restmessenger.model.Message;

public class MessageService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();

	public MessageService() {
		messages.put(1L, new Message(1, "Hello World!", "Sandeep"));
		messages.put(2L, new Message(2, "Welcome to the World!", "Sandeep"));
	}

	public List<Message> getAllMessages() {
		/*Message m1 = new Message(1L, "Hello World!", "Sandeep");
		Message m2 = new Message(2L, "Welcome to the World!", "Sandeep");

		List<Message> messageList = new ArrayList<>();
		messageList.add(m1);
		messageList.add(m2);

		return messageList;*/
		return new ArrayList<Message>(messages.values());
	}

	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messageForYear = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		for(Message message: messages.values()) {
			calendar.setTime(message.getCreated());
			if(calendar.get(Calendar.YEAR) == year) {
				messageForYear.add(message);
			}
		}
		return messageForYear;
	}

	public List<Message> getAllMessagesPaginated(int start, int size) {
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if(start + size > list.size()) return new ArrayList<>();
		return list.subList(start, start + size);
	}

	public Message getMessage(long id) {
		return messages.get(id);
	}

	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		if(message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}

	public void removeMessage(long id) {
		messages.remove(id);
	}
}
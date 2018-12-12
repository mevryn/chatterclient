package application;

public interface Subject {
    void register(ChatListener chatListener);

    void unregister(ChatListener chatListener);

    void notifyObservers(Message message);
}

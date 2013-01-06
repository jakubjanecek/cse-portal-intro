package j;

public final class Event {

    private final long id;

    private final String name;

    private final long when;

    private final EventSource source;
    
    public static Event Event(long id, String name, long when, EventSource source) {
        return new Event(id, name, when, source);
    }

    public Event(long id, String name, long when, EventSource source) {
        this.id = id;
        this.name = name;
        this.when = when;
        this.source = source;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getWhen() {
        return when;
    }

    public EventSource getSource() {
        return source;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", when=" + when +
                ", source=" + source +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (id != event.id) return false;
        if (when != event.when) return false;
        if (!name.equals(event.name)) return false;
        if (!source.equals(event.source)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + (int) (when ^ (when >>> 32));
        result = 31 * result + source.hashCode();
        return result;
    }
}

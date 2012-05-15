package org.ardverk.gibson.dashboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.ardverk.gibson.core.Event;

@Singleton
class DefaultEventService implements EventService {
  
  @Inject
  private EventDAO eventDAO;
  
  @Override
  public void drop() {
    eventDAO.clear();
  }

  @Override
  public TypeItems getTypeItems() {
    List<String> typeNames = eventDAO.getTypeNames();
    List<TypeItem> dst = new ArrayList<TypeItem>(typeNames.size());
    
    long total = 0L;
    for (String typeName : typeNames) {
      long count = eventDAO.getTypeNameCount(typeName);
      dst.add(new TypeItem(typeName, count));
      total += count;
    }
    
    Collections.sort(dst, Countable.DESCENDING);
    return new TypeItems(dst, total);
  }

  @Override
  public EventItems getEventItems(String typeName) {
    List<Event> events = eventDAO.getEvents(typeName);
    List<EventItem> dst = new ArrayList<EventItem>(events.size());
    
    long total = 0L;
    for (Event event : events) {
      long count = eventDAO.getEventCount(event);
      dst.add(new EventItem(event, count));
      total += count;
    }
    
    Collections.sort(dst, Countable.DESCENDING);
    return new EventItems(typeName, dst, total);
  }
  
  @Override
  public Event getEvent(String typeName, String signature) {
    return eventDAO.getEvent(typeName, signature);
  }

  @Override
  public long getEventCount(Event event) {
    return eventDAO.getEventCount(event);
  }
}
package controllers;

import static org.ardverk.gibson.dashboard.Context.injector;

import org.ardverk.gibson.core.Event;
import org.ardverk.gibson.dashboard.EventItems;
import org.ardverk.gibson.dashboard.EventService;
import org.ardverk.gibson.dashboard.TypeItems;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.event;
import views.html.events;
import views.html.types;

public class Application extends Controller {
  
  public static Result drop() {
    EventService service = injector().getInstance(EventService.class);
    service.drop();
    return redirect("/");
  }
  
  public static Result types() {
    EventService service = injector().getInstance(EventService.class);
    TypeItems items = service.getTypeItems();
    return ok(types.render(items));
  }
  
  public static Result events(String typeName) {
    EventService service = injector().getInstance(EventService.class);
    EventItems items = service.getEventItems(typeName);
    
    if (items == null || items.isEmpty()) {
      return notFound(typeName);
    }
    
    return ok(events.render(items));
  }
  
  public static Result event(String typeName, String signature) {
    EventService service = injector().getInstance(EventService.class);
    Event item = service.getEvent(typeName, signature);
    
    if (item == null) {
      return notFound(typeName + ", " + signature);
    }
    
    long count = service.getEventCount(item);
    return ok(event.render(item, count));
  }
}
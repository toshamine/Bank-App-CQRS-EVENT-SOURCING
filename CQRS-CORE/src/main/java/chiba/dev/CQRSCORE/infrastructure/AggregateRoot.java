package chiba.dev.CQRSCORE.infrastructure;

import chiba.dev.CQRSCORE.events.BaseEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public abstract class AggregateRoot {
    protected String id;
    protected Long version = -1l;

    private List<BaseEvent> unCommittedChanges = new ArrayList<>();

    public void markChangesAsCommitted(){
        unCommittedChanges.clear();
    }

    public void applyChanges(BaseEvent event,Boolean isNewEvent){
        try{
            var method = getClass().getDeclaredMethod("apply",event.getClass());
            method.setAccessible(true);
            method.invoke(this,event);
        }
        catch (NoSuchMethodException exception){
            exception.printStackTrace();
            log.warn(exception.getMessage());
        }
        catch (Exception exception){
            exception.printStackTrace();
            log.warn(exception.getMessage());
        }
        finally {
            if(isNewEvent)
                unCommittedChanges.add(event);
        }
    }

    public void raiseEvent(BaseEvent event){
        applyChanges(event,true);
    }

    public void replayEvents(Iterable<BaseEvent> events){
        events.forEach(event -> applyChanges(event,false));
    }
}

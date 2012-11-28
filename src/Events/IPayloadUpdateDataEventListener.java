package Events;

import java.util.EventListener;

public interface IPayloadUpdateDataEventListener extends EventListener
{
	public void PayloadUpdateDataEventHandeler (PayloadUpdataDataEvent event);
}

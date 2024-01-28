
## Coop Hatch Proxy

I have a chicken coop with an "automatic" hatch powered by an raspberry pi and stepper motor. 

It can be open by sending a request on my local network.

But i need to be able to open it when away from the local network soo.. 

The easiest solution for this was to set up a topic on: https://ntfy.sh/

### Flow

1. I have an app on my phone that publish on the topic by: 

```
let req = new Request("https://ntfy.sh/<topic>");
req.method = "post";
req.body = "open_hatch" _OR_ "cloas_hatch";
req.load();
```

2. This app then listening on the topic in the HttpStreamService for open/close command.

3. To send it to the WebclientForHatchControl to forward it to the raspberry pi.

### Scheduler

Since it is nice with automation, a scheduler is added to open and close in the morning and evening. 

### Run!

To be able to run localy, this needs to be set: 
```
-Dntfy.sh.topic=<topic>/raw
```
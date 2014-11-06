webhookservlet
==============

Deploy this servlet on your tomcat to call a local script when git gets a push.

1. Deploy the war file on your tomcat. 

2. Create a script /opt/webhook/webhook.sh, with content similar to:

---
#!/bin/bash

echo 'This is a test' >> /tmp/webhook.log
cd /tmp
git clone https://github.com/mobilars/webhookservlet.git
---

3. Create a webhook on git, pointing to your URL (configure for push events only)

http://<servername>/webhook/WebhookServlet
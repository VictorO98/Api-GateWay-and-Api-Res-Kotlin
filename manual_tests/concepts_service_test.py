import json
import datetime
import http.client
import urllib.parse
from time import time

########################################################################################################################
##################################################### ENVIRONMENTS #####################################################
########################################################################################################################

# local
conn = http.client.HTTPConnection("192.168.56.117:8080")

login_get = {
}
params = [{'vocabularyId': '44829696'}]
json_data_post = json.dumps(login_get)
conn.request("GET", "/concepts", params,  headers={'Content-type': 'application/json'})

start = datetime.datetime.now()
res = conn.getresponse()
end = datetime.datetime.now()

data = res.read()

elapsed = end - start

print(data.decode("utf-8"))
print("\"" + str(res.status) + "\"")
print("\"" + str(res.reason) + "\"")
print("\"elapsed seconds: " + str(elapsed) + "\"")
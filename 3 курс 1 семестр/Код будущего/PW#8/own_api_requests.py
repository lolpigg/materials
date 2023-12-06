import json
import urllib.request
import urllib.parse

factorial_request = {"number": 5}
params = json.dumps(factorial_request).encode('utf-8')
req = urllib.request.Request("http://127.0.0.1:8000/factorial/", data=params, headers={'content-type': 'application/json'})
response = urllib.request.urlopen(req)
print(response.read().decode('utf-8'))

probability_request = {"event1_probability": 0.3, "event2_probability": 0.5}
params = json.dumps(probability_request).encode('utf-8')
req = urllib.request.Request("http://127.0.0.1:8000/probability/", data=params, headers={'content-type': 'application/json'})
response = urllib.request.urlopen(req)
print(response.read().decode('utf-8'))

volume_request = {"length": 4.0, "width": 3.0, "height": 5.0}
params = json.dumps(volume_request).encode('utf-8')
req = urllib.request.Request("http://127.0.0.1:8000/parallelepiped_capacity/", data=params, headers={'content-type': 'application/json'})
response = urllib.request.urlopen(req)
print(response.read().decode('utf-8'))

triangle_request = {"side_a": 3, "side_b": 4}
params = json.dumps(triangle_request).encode('utf-8')
req = urllib.request.Request("http://127.0.0.1:8000/triangle_calculation/", data=params, headers={'content-type': 'application/json'})
response = urllib.request.urlopen(req)
print(response.read().decode('utf-8'))

interest_request = {"start_summ": 1000, "year_procent": 0.05, "number_of_payments": 12, "years" : 2}
params = json.dumps(interest_request).encode('utf-8')
req = urllib.request.Request("http://127.0.0.1:8000/hard_procent/", data=params, headers={'content-type': 'application/json'})
response = urllib.request.urlopen(req)
print(response.read().decode('utf-8'))

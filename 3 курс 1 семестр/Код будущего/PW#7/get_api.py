import json
from urllib import request

def get_holidays(country_code):
    url = f"https://date.nager.at/api/v3/PublicHolidays/2023/{country_code}"
    with request.urlopen(url) as response:
        data = response.read()
        holidays_data = json.loads(data)
        return holidays_data


country_code = input("Введите код страны: ")
holidays = get_holidays(country_code)
if isinstance(holidays, list):
    for holiday in holidays:
        print(f"{holiday['date']}: {holiday['name']}")
else:
    print(holidays)

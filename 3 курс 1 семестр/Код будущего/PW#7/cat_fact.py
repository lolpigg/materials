from urllib import request
import json

#У меня не работали requests, поэтому все обращения я сделал через библиотеку urllib
def get_cat_fact(route):
    url = f"https://catfact.ninja{route}"
    with request.urlopen(url) as response:
        data = response.read()
        cat_data = json.loads(data)
        return cat_data

def main():
    print("Доступные маршруты:\n1. /fact\n2. /fact?max_length=140\n3. /fact?max_length=140&limit=5")
    choice = input("Введите номер маршрута (например, /fact): ")
    cat_data = get_cat_fact(choice)
    print("Результат:")
    print(cat_data)

if __name__ == "__main__":
    main()

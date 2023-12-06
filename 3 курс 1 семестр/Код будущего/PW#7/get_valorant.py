import urllib.request
import json


def print_agent_info():
    url = "https://valorant-api.com/v1/agents"
    response = urllib.request.urlopen(url)
    data = json.load(response)
    agents = data["data"]
    for agent in agents:
        print(f"Имя персонажа: {agent['displayName']}")
        print(f"Описание: {agent['description']}")


def print_map_info():
    url = "https://valorant-api.com/v1/maps"
    response = urllib.request.urlopen(url)
    data = json.load(response)
    maps = data["data"]
    for game_map in maps:
        print(f"Название карты: {game_map['displayName']}")
        print(f"Описание: {game_map['narrativeDescription']}")


def print_weapon_info():
    url = "https://valorant-api.com/v1/weapons"
    response = urllib.request.urlopen(url)
    data = json.load(response)
    weapons = data["data"]
    for weapon in weapons:
        print(f"Название оружия: {weapon['displayName']}")
        print(f"Категория оружия: {weapon['category']}")


def print_event_info():
    url = "https://valorant-api.com/v1/events"
    response = urllib.request.urlopen(url)
    data = json.load(response)
    events = data["data"]
    for event in events:
        print(f"Название события: {event['displayName']}")
        print(f"Начало в {event['startTime']}")


print_agent_info()
print("\n\n\n\n")
print_map_info()
print("\n\n\n\n")
print_weapon_info()
print("\n\n\n\n")
print_event_info()
# Позволяет изменять цвет текста
import colorama
from colorama import Fore, Back, Style

# Работа с датой и временем
import time, datetime


# Работа с json файлами
import json

from web3 import Web3

from web3.middleware import geth_poa_middleware

#Cоздаем экзмемпляр подключения
web3 = Web3(Web3.HTTPProvider('http://127.0.0.1:7545'))

# Считываем abi смарт-контракта и помещаем данные из файла в переменную abi
with open("abi.txt", "r") as file:
    abi = json.loads(file.read())

# Адрес смарт-контракта
address = "0x8573D2a9bEF97142421Fb0e543E8771a4a9767A5"

# Указываем что это адрес контракта
contract_address = Web3.to_checksum_address(address)

web3.middleware_onion.inject(geth_poa_middleware, layer=0)

# Создаем объект контракта
contract = web3.eth.contract(address=address, abi=abi)
def reg():
    # получаем параметры от пользователя
    admin_login = input('Введите логин администратора: ')
    admin_password = input('Введите пароль администратора: ')
    user_adr = input('Введите адрес пользователя: ')
    admin_word = input('Введите секретное слово администратора: ')
    user_word = input('Введите пароль пользователя: ')
    user_login = input('Введите логин пользователя: ')
    user_password = input('Введите пароль пользователя: ')
    user_role_id = int(input('Введите ID роли пользователя: '))
    while not isinstance(user_role_id, int):
        try:
            user_role_id = int(input('Введите ID роли пользователя: '))
        except ValueError:
            print('ID роли должен быть целым числом. Попробуйте еще раз.')

    shop_id = int(input('Введите ID магазина: '))
    while not isinstance(shop_id, int):
        try:
            shop_id = int(input('Введите ID магазина: '))
        except ValueError:
            print('ID магазина должен быть целым числом. Попробуйте еще раз.')
    fio = input('Введите ФИО пользователя: ')

    # отправляем транзакцию на вызов функции
    tx_hash = contract.functions.user_register(
        admin_login,
        admin_password,
        user_adr,
        admin_word,
        user_word,
        user_login,
        user_password,
        user_role_id,
        shop_id,
        fio
    ).transact({'from': web3.eth.accounts[0]})

    # ожидаем подтверждения транзакции
    tx_receipt = web3.eth.waitForTransactionReceipt(tx_hash)
def chr():
    # получаем параметры от пользователя
    admin_login = input('Введите логин администратора: ')
    admin_password = input('Введите пароль администратора: ')
    user_adr = input('Введите адрес пользователя: ')
    admin_word = input('Введите секретное слово администратора: ')
    user_role_id = int(input('Введите ID роли пользователя: '))
    while not isinstance(user_role_id, int):
        try:
            user_role_id = int(input('Введите ID роли пользователя: '))
        except ValueError:
            print('ID роли должен быть целым числом. Попробуйте еще раз.')

    shop_id = int(input('Введите ID магазина: '))
    while not isinstance(shop_id, int):
        try:
            shop_id = int(input('Введите ID магазина: '))
        except ValueError:
            print('ID магазина должен быть целым числом. Попробуйте еще раз.')

    # отправляем транзакцию на вызов функции
    tx_hash = contract.functions.change_role(
        admin_login,
        admin_password,
        user_adr,
        admin_word,
        user_role_id,
        shop_id,
    ).transact({'from': web3.eth.accounts[0]})

    # ожидаем подтверждения транзакции
    tx_receipt = web3.eth.waitForTransactionReceipt(tx_hash)

def fdb():
    # получаем параметры от пользователя
    admin_login = input('Введите логин: ')
    admin_password = input('Введите пароль: ')
    name = input('Введите название: ')
    admin_word = input('Введите секретное слово: ')
    user_role_id = int(input('Введите ID роли пользователя: '))
    while not isinstance(user_role_id, int):
        try:
            user_role_id = int(input('Введите ID роли пользователя: '))
        except ValueError:
            print('ID роли должен быть целым числом. Попробуйте еще раз.')

    shop_id = int(input('Введите ID магазина: '))
    while not isinstance(shop_id, int):
        try:
            shop_id = int(input('Введите ID магазина: '))
        except ValueError:
            print('ID магазина должен быть целым числом. Попробуйте еще раз.')

    # отправляем транзакцию на вызов функции
    tx_hash = contract.functions.add_feedback(
        admin_login,
        admin_password,
        admin_word,
        name,
        user_role_id,
        shop_id,
    ).transact({'from': web3.eth.accounts[0]})

    # ожидаем подтверждения транзакции
    tx_receipt = web3.eth.waitForTransactionReceipt(tx_hash)
def main():
    while True:
        print("В рамках данной платформы вы можете сделать следующее:\n1 - Зарегистрировать пользователя\n2 - Сменить роль\n3 - Добавить отзыв\n4 - Добавить ответ на отзыв\n5 - Добавить запрос\n6 - Ответить на запрос\n7 - Добавить лайк\n8 - Добавить дизлайк\n9 - Сменить роль на покупателя\n10 - Вывести информацию\n11 - Вывести баланс\n12 - Вывести кол-во дизлайков\n13 - Вывести кол-во лайков")
        action = int(input())
        match(action):
            case 1:
                reg()
            case 2:
                chr()
            case 3:
                fdb()
            case 4:
                pass
            case 5:
                pass
            case 6:
                pass
            case 7:
                pass
            case 8:
                pass
            case 9:
                pass
            case 10:
                pass
            case 11:
                pass
            case 12:
                pass
            case 13:
                pass
            case _:
                print("Вы выбрали неправильную операцию")

if __name__ == '__main__':
    print("Здравствуйте!")
    main()
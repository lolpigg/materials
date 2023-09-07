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
address = "0xf1C3624b0B57b14EC1c38cfD8af004d1b1791354"

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
    user_word = input('Введите секретное слово пользователя: ')
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
    try:
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
        tx_receipt = web3.eth.wait_for_transaction_receipt(tx_hash)
    except:
        print("Ошибка! Проверьте введенные данные.")

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
    try:
        # отправляем транзакцию на вызов функции
        tx_hash = contract.functions.change_role(
            admin_login,
            admin_password,
            user_adr,
            admin_word,
            user_role_id,
            shop_id,
        ).transact({'from': web3.eth.accounts[0]})
        tx_receipt = web3.eth.wait_for_transaction_receipt(tx_hash)
    except:
        print("Ошибка! Проверьте введенные данные.")
    # ожидаем подтверждения транзакции

def fdb():
    # получаем параметры от пользователя
    admin_login = input('Введите логин: ')
    admin_password = input('Введите пароль: ')
    name = input('Введите название: ')
    admin_word = input('Введите секретное слово: ')
    description = input('Введите описание: ')
    mark = input('Введите оценку: ')
    while not isinstance(mark, int):
        try:
            mark = int(input('Введите оценку: '))
        except ValueError:
            print('Оценка должна быть целым числом. Попробуйте еще раз.')
    shop_id = int(input('Введите ID магазина: '))
    while not isinstance(shop_id, int):
        try:
            shop_id = int(input('Введите ID магазина: '))
        except ValueError:
            print('ID магазина должен быть целым числом. Попробуйте еще раз.')
    try:
        # отправляем транзакцию на вызов функции
        tx_hash = contract.functions.add_feedback(
            admin_login,
            admin_password,
            admin_word,
            name,
            description,
            mark,
            shop_id,
        ).transact({'from': web3.eth.accounts[0]})

        # ожидаем подтверждения транзакции
        tx_receipt = web3.eth.wait_for_transaction_receipt(tx_hash)
    except:
        print("Ошибка! Проверьте введенные данные.")
def a_fdb():
    # получаем параметры от пользователя
    admin_login = input('Введите логин: ')
    admin_password = input('Введите пароль: ')
    name = input('Введите название: ')
    admin_word = input('Введите секретное слово: ')
    description = input('Введите описание: ')
    shop_id = int(input('Введите ID фидбека: '))
    while not isinstance(shop_id, int):
        try:
            shop_id = int(input('Введите ID фидбека: '))
        except ValueError:
            print('ID магазина должен быть целым числом. Попробуйте еще раз.')
    try:
        # отправляем транзакцию на вызов функции
        tx_hash = contract.functions.add_answer(
            admin_login,
            admin_password,
            admin_word,
            name,
            description,
            shop_id,
        ).transact({'from': web3.eth.accounts[0]})

        # ожидаем подтверждения транзакции
        tx_receipt = web3.eth.wait_for_transaction_receipt(tx_hash)
    except:
        print("Ошибка! Проверьте введенные данные.")

def rq():
    # получаем параметры от пользователя
    admin_login = input('Введите логин: ')
    admin_password = input('Введите пароль: ')
    admin_word = input('Введите секретное слово: ')
    shop_id = int(input('Введите ID желаемой роли: '))
    while not isinstance(shop_id, int):
        try:
            shop_id = int(input('Введите ID роли: '))
        except ValueError:
            print('ID роли должен быть целым числом. Попробуйте еще раз.')
    fio = input('Введите описание запроса: ')
    try:
        # отправляем транзакцию на вызов функции
        tx_hash = contract.functions.add_request(
            admin_login,
            admin_password,
            admin_word,
            shop_id,
            fio
        ).transact({'from': web3.eth.accounts[0]})
        tx_receipt = web3.eth.wait_for_transaction_receipt(tx_hash)
    except:
        print("Ошибка! Проверьте введенные данные.")
def a_rq():
    # получаем параметры от пользователя
    admin_login = input('Введите логин: ')
    admin_password = input('Введите пароль: ')
    admin_word = input('Введите секретное слово: ')
    shop_id = int(input('Введите ID запроса: '))
    while not isinstance(shop_id, int):
        try:
            shop_id = int(input('Введите ID запроса: '))
        except ValueError:
            print('ID запроса должен быть целым числом. Попробуйте еще раз.')
    fio = input('Введите ответ на запрос (1 - принять, 0 - отклонить): ')
    if fio == 1:
        answ = True
    elif fio == 0:
        answ = False
    else:
        print("Ответ неверный.")
        return
    try:
        # отправляем транзакцию на вызов функции
        tx_hash = contract.functions.answer_on_request(
            admin_login,
            admin_password,
            admin_word,
            shop_id,
            answ
        ).transact({'from': web3.eth.accounts[0]})
        tx_receipt = web3.eth.wait_for_transaction_receipt(tx_hash)
    except:
        print("Ошибка! Проверьте введенные данные.")

def like():
    admin_login = input('Введите логин: ')
    admin_password = input('Введите пароль: ')
    admin_word = input('Введите секретное слово: ')
    shop_id = int(input('Введите ID фидбека или ответа на него: '))
    while not isinstance(shop_id, int):
        try:
            shop_id = int(input('Введите ID фидбека или ответа на него: '))
        except ValueError:
            print('ID фидбека или ответа должен быть целым числом. Попробуйте еще раз.')
    fio = input('Фидбек или ответ? (1 - фидбек, 0 - ответ): ')
    if fio == 1:
        answ = False
    elif fio == 0:
        answ = True
    else:
        print("Ответ неверный.")
        return
    try:
        # отправляем транзакцию на вызов функции
        tx_hash = contract.functions.add_like(
            admin_login,
            admin_password,
            admin_word,
            shop_id,
            answ
        ).transact({'from': web3.eth.accounts[0]})
        tx_receipt = web3.eth.wait_for_transaction_receipt(tx_hash)
    except:
        print("Ошибка! Проверьте введенные данные.")

def dislike():
    admin_login = input('Введите логин: ')
    admin_password = input('Введите пароль: ')
    admin_word = input('Введите секретное слово: ')
    shop_id = int(input('Введите ID фидбека или ответа на него: '))
    while not isinstance(shop_id, int):
        try:
            shop_id = int(input('Введите ID фидбека или ответа на него: '))
        except ValueError:
            print('ID фидбека или ответа должен быть целым числом. Попробуйте еще раз.')
    fio = input('Фидбек или ответ? (1 - фидбек, 0 - ответ): ')
    if fio == 1:
        answ = False
    elif fio == 0:
        answ = True
    else:
        print("Ответ неверный.")
        return
    try:
        # отправляем транзакцию на вызов функции
        tx_hash = contract.functions.add_dislike(
            admin_login,
            admin_password,
            admin_word,
            shop_id,
            answ
        ).transact({'from': web3.eth.accounts[0]})
        tx_receipt = web3.eth.wait_for_transaction_receipt(tx_hash)
    except:
        print("Ошибка! Проверьте введенные данные.")

def sw():
    admin_login = input('Введите логин: ')
    admin_password = input('Введите пароль: ')
    admin_word = input('Введите секретное слово: ')
    try:
        # отправляем транзакцию на вызов функции
        tx_hash = contract.functions.switchToBuyer(
            admin_login,
            admin_password,
            admin_word,
        ).transact({'from': web3.eth.accounts[0]})
        tx_receipt = web3.eth.wait_for_transaction_receipt(tx_hash)
    except:
        print("Ошибка! Проверьте введенные данные.")
def si():
    adr = input('Введите ваш адрес аккаунта: ')
    try:
        # отправляем транзакцию на вызов функции
        tx_hash = contract.functions.see_info().transact({'from': adr})
        print(tx_hash)
        tx_receipt = web3.eth.wait_for_transaction_receipt(tx_hash)
    except:
        print("Ошибка! Проверьте введенные данные.")

def sb():
    adr = input('Введите ваш адрес аккаунта: ')
    try:
        # отправляем транзакцию на вызов функции
        tx_hash = contract.functions.see_balance().transact({'from': adr})
        tx_receipt = web3.eth.wait_for_transaction_receipt(tx_hash)
    except:
        print("Ошибка! Проверьте введенные данные.")

def sl():
    shop_id = int(input('Введите ID фидбека или ответа на него: '))
    while not isinstance(shop_id, int):
        try:
            shop_id = int(input('Введите ID фидбека или ответа на него: '))
        except ValueError:
            print('ID фидбека или ответа должен быть целым числом. Попробуйте еще раз.')
    fio = input('Фидбек или ответ? (1 - фидбек, 0 - ответ): ')
    if fio == 1:
        answ = False
    elif fio == 0:
        answ = True
    else:
        print("Ответ неверный.")
        return
    try:
        # отправляем транзакцию на вызов функции
        tx_hash = contract.functions.see_likes(
            shop_id,
            answ,
        ).transact({'from': web3.eth.accounts[0]})
        tx_receipt = web3.eth.wait_for_transaction_receipt(tx_hash)
    except:
        print("Ошибка! Проверьте введенные данные.")
def sd():
    shop_id = int(input('Введите ID фидбека или ответа на него: '))
    while not isinstance(shop_id, int):
        try:
            shop_id = int(input('Введите ID фидбека или ответа на него: '))
        except ValueError:
            print('ID фидбека или ответа должен быть целым числом. Попробуйте еще раз.')
    fio = input('Фидбек или ответ? (1 - фидбек, 0 - ответ): ')
    if fio == 1:
        answ = False
    elif fio == 0:
        answ = True
    else:
        print("Ответ неверный.")
        return
    try:
        # отправляем транзакцию на вызов функции
        tx_hash = contract.functions.see_dislikes(
            shop_id,
            answ,
        ).transact({'from': web3.eth.accounts[0]})
        tx_receipt = web3.eth.wait_for_transaction_receipt(tx_hash)
    except:
        print("Ошибка! Проверьте введенные данные.")
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
                a_fdb()
            case 5:
                rq()
            case 6:
                a_rq()
            case 7:
                like()
            case 8:
                dislike()
            case 9:
                sw()
            case 10:
                si()
            case 11:
                sb()
            case 12:
                sl()
            case 13:
                sd()
            case _:
                print("Вы выбрали неправильную операцию")

if __name__ == '__main__':
    print("Здравствуйте!")
    main()
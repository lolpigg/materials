import string_operations
import arithmetic


def calculate_age_in_days(full_age):
    current_year = 2023
    birth_year = current_year - full_age

    def is_leap_year(year):
        return (year % 4 == 0 and year % 100 != 0) or (year % 400 == 0)

    days = 0
    for year in range(birth_year, current_year + 1):
        if is_leap_year(year):
            days += 366
        else:
            days += 365
    return days


def hard_procent(P=1000, r=0.05, n=1, t=1):
    result = P * ((1 + r / n) ** (n * t))
    return result


def count_substrings(main_string, sub_string):
    return main_string.count(sub_string)


def main():
    age_in_days = calculate_age_in_days(25)
    print("Прожито дней:", age_in_days)
    interest_amount = hard_procent(1500, 0.06, 1, 2)
    print("Сумма на конец периода:", interest_amount)
    substring_count = count_substrings('abababab', 'ab')
    print("Количество подстрок:", substring_count)

    discriminant = arithmetic.calculate_discriminant(1, -3, 2)
    print("Дискриминант:", discriminant)
    roots = arithmetic.calculate_roots(1, -3, 2)
    print("Корни уравнения:", roots)
    joint_probability = arithmetic.calculate_probability_of_joint_events(0.5, 0.5)
    print("Вероятность совместных событий:", joint_probability)
    independent_probability = arithmetic.calculate_probability_of_independent_events(0.5, 0.5)
    print("Вероятность несовместных событий:", independent_probability)

    char_index = string_operations.find_character_in_string('l', 'Hello')
    print("Индекс символа:", char_index)
    uppercase_letters = string_operations.get_uppercase_letters("Hello, World!")
    print("Кол-во заглавных символов:", uppercase_letters)
    lowercase_letters = string_operations.get_lowercase_letters("Hello, World!")
    print("Кол-во строчных символов:", lowercase_letters)
    numeric_characters = string_operations.get_numeric_characters("Hello 123")
    print("Кол-во числовых символов:", numeric_characters)
    result1 = string_operations.is_uppercase('H')
    print("Это заглавная буква? - ", result1)
    result2 = string_operations.is_lowercase('h')
    print("Это строчная буква? - ", result2)
    result3 = string_operations.is_numeric('5')
    print("Это цифра? - ", result3)
    substring_count = string_operations.count_substrings('abababab', 'ab')
    print("Количество подстрок:", substring_count)


if __name__ == '__main__':
    main()

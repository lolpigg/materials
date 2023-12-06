def find_character_in_string(search_char, input_string):
    return input_string.find(search_char)


def get_uppercase_letters(input_string):
    a = 0
    for ch in input_string:
        if ch.isupper():
            a += 1
    return a


def get_lowercase_letters(input_string):
    a = 0
    for ch in input_string:
        if ch.islower():
            a += 1
    return a


def get_numeric_characters(input_string):
    a = 0
    for ch in input_string:
        if ch.isdigit():
            a += 1
    return a


def is_uppercase(character):
    return character.isupper()


def is_lowercase(character):
    return character.islower()


def is_numeric(character):
    return character.isdigit()


def count_substrings(main_string, sub_string):
    return main_string.count(sub_string)

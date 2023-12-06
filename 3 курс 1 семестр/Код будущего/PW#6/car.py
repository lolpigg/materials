from art import *
class Car:
    def __init__(self, make, model, year):
        self.make = make
        self.model = model
        self.year = year

    def display_info(self):
        result = text2art(f"{self.year} {self.make} {self.model}", font='starwars')
        return result

    def change_name(self, model):
        self.model = model

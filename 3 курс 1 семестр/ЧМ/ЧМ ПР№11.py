import numpy as np
from scipy.interpolate import CubicSpline

# Задаем значения x и y
number_in_journal = 12
x = (3.9 * number_in_journal / 30) - 2
list_of_x = np.array([-2, -1, 0, 1, 2])
list_of_y = np.array([1.1, 1.9, 3.2, 3.8, 4.5])

# Создаем объект CubicSpline
cs = CubicSpline(list_of_x, list_of_y)

# Вычисляем значение функции в точке -0.44
result = cs(-0.44)
print(f"Значение функции в точке {x}: ", result)
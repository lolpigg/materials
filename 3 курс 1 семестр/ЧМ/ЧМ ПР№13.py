from scipy import integrate
import numpy as np

def f(x):
    return x**2 + 2 + np.sqrt(x)

log_a = 0
log_b = 2
result, _ = integrate.fixed_quad(f, log_a, log_b, n=4)

print("Значение интеграла:", result)

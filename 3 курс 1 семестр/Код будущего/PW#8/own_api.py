from fastapi import FastAPI, Response
from pydantic import BaseModel

app = FastAPI()

class FactorialRequest(BaseModel):
    number: int

class ProbabilityRequest(BaseModel):
    event1_probability: float
    event2_probability: float

class ParallelepipedVolumeRequest(BaseModel):
    length: float
    width: float
    height: float

class TriangleCalculationRequest(BaseModel):
    side_a: float = None
    side_b: float = None
    hypotenuse: float = None

class CompoundInterestRequest(BaseModel):
    start_summ: float
    year_procent: float
    number_of_payments: int
    years: int

@app.post("/factorial/")
async def factorial_calculation(request: FactorialRequest):
    result = 1
    for i in range(1, request.number + 1):
        result *= i
    return {"factorial": result}

@app.post("/probability/")
async def probability_calculation(request: ProbabilityRequest):
    result = request.event1_probability * request.event2_probability
    return {"probability": result}

@app.post("/parallelepiped_capacity/")
async def parallelepiped_capacity_calculation(request: ParallelepipedVolumeRequest):
    return {"capacity": request.length * request.width * request.height}

@app.post("/triangle_calculation/")
async def triangle_calculation(request: TriangleCalculationRequest):
    if request.side_a is not None and request.side_b is not None:
        result = (request.side_a ** 2 + request.side_b ** 2) ** 0.5
        return {"hypotenuse": result}
    elif request.side_a is not None and request.hypotenuse is not None:
        result = (request.hypotenuse ** 2 - request.side_a ** 2) ** 0.5
        return {"side_b": result}
    elif request.side_b is not None and request.hypotenuse is not None:
        result = (request.hypotenuse ** 2 - request.side_b ** 2) ** 0.5
        return {"side_a": result}
    else:
        return {"error": "Отправлены неверные данные"}


@app.post("/hard_procent/")
async def hard_procent_calc(request: CompoundInterestRequest):
    result = request.start_summ * ((1 + request.year_procent / request.number_of_payments) ** (request.number_of_payments * request.years))
    return {"hard_procent": result}


# Запуск сервера с помощью uvicorn
if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)
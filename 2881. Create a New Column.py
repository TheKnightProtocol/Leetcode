import pandas as pd

def createBonusColumn(employees):
    # Create the 'bonus' column with doubled salary values
    employees['bonus'] = employees['salary'] * 2
    return employees

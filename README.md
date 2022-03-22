# Exercise

#  Allows an API caller to retrieve the reference rate data for a given Date

http://localhost:8080/exchange/allquotes?date=2022-03-14

# Given a Date, source Currency (eg. JPY), target Currency (eg. GBP)

localhost:8080/exchange/convert?date=2022-03-15&targetCurrency=BRL&sourceCurrency=USD&amount=250

# Given a start Date, an end Date and a Currency, return the highest reference

localhost:8080/exchange/highest?startDate=2022-03-02&endDate=2022-03-17&currency=USD

# Given a start Date, an end Date and a Currency, determine and return the average

localhost:8080/exchange/highest?startDate=2022-03-02&endDate=2022-03-17&averageValue=USD

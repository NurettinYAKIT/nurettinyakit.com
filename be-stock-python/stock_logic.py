from flask import Flask, request, jsonify
from iexfinance.stocks import Stock

app = Flask(__name__)

@app.route("/python/stock", methods=['POST'])
def stock_logic():
    ticker = request.get_json()['symbol']

    try:
        stock = Stock(ticker.encode("utf-8"))
        price = stock.get_price()
    except Exception as e :
        print("This is an error message! " +  str(e) )

    return jsonify(
        ticker=ticker,
        price=price
    )

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
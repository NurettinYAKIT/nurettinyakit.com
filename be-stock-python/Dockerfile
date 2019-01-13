FROM python:2-alpine

ADD stock_logic.py /

RUN pip install --upgrade pip && \
    pip install pip-tools && \
    pip install -U setuptools

RUN apk add --update curl gcc g++ && \
    ln -s /usr/include/locale.h /usr/include/xlocale.h

RUN pip install Flask
RUN pip install requests
RUN pip install jsonify
RUN pip install --no-cache-dir pandas
RUN pip install iexfinance

CMD [ "python", "./stock_logic.py" ]

EXPOSE 5000

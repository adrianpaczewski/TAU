import requests
import json
import unittest
from datetime import date


class TestApi(unittest.TestCase):
    url = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies.json"
    response = requests.request("GET", url)
    a = json.loads(response.text)

    def test_get_currencies_check_status_code_is_200(self):

        self.assertEqual(self.response.status_code, 200)

    def test_if_currencies_keys_exists(self):

        expected = ['btc', 'eur', 'gbp', 'pln', 'usd']
        current = []

        for key, value in self.a.items():
            if key in expected:
                current.append(key)

        self.assertEqual(self.response.status_code, 200)
        self.assertEqual(expected, current)

    def test_if_currencies_values_exists(self):

        currency_euro = 'Euro'
        currency_pln = ''
        currency_usd = ''

        for key, value in self.a.items():
            if key == 'eur':
                currency_euro = value
            if key == 'pln':
                currency_pln = value
            if key == 'usd':
                currency_usd = value

        self.assertEqual('Euro', currency_euro)
        self.assertEqual('Poland złoty', currency_pln)
        self.assertEqual('United States dollar', currency_usd)

    def test_get_currencies_check_status_code_is_403_for_invalid_parameter(self):

        parameter = 'euro'
        url = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/{}.json".format(parameter)
        response = requests.request("GET", url)

        self.assertEqual(response.status_code, 403)

    def test_get_currencies_check_total_number_of_currencies_is_correct(self):

        self.assertEqual(181, len(self.a))

    def test_get_currencies_for_eur_check_content_type_equals_json(self):

        self.assertEqual(self.response.headers["Content-Type"], "application/json; charset=utf-8")

    def test_get_currency_value_for_eur_to_usd_check_response_exists(self):

        url = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/eur/usd.json"
        response = requests.request("GET", url)
        a = json.loads(response.text)
        self.assertTrue(a["usd"] > 0)

    def test_get_currency_value_for_eur_to_usd_check_value_is_correct(self):

        url = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/2021-12-01/currencies/eur/usd.json"
        response = requests.request("GET", url)
        a = json.loads(response.text)

        eur_to_usd_rate = a["usd"]

        url = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/2021-12-01/currencies/usd/eur.json"
        response = requests.request("GET", url)
        a = json.loads(response.text)

        usd_to_eur_rate = a["eur"]

        self.assertEqual(1.133421, eur_to_usd_rate)
        self.assertEqual(0.882285, usd_to_eur_rate)

    def test_get_currencies_for_eur_in_latest_date_check_date_is_current(self):

        parameter = 'eur'
        url = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/{}.json".format(parameter)
        response = requests.request("GET", url)
        a = json.loads(response.text)

        self.assertEqual(date.today().isoformat(), a["date"])

    def test_get_currency_value_for_usd_to_usd_check_return_value_is_always_one(self):

        parameter = 'eur'
        url = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/{}.json".format(parameter)
        response = requests.request("GET", url)
        a = json.loads(response.text)

        self.assertEqual(1, a['eur']['eur'])

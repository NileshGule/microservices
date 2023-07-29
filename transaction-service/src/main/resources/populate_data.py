
def data_row(x):
    suffix = str(x)
    TXN_ID=  '89d3o179-blbc-465b-o9ee-e2d5f6of'+ suffix
    AMOUNT='UGD 65-'
    ACCT_IBAN='CH93-0000-0000-9345'+ suffix
    VALUE_DATE='01-10-2020'
    DESC='Online payment CHF - ' + suffix

    # row =  TXN_ID + ',' + AMOUNT + ',' +  ACCT_IBAN + ',' + VALUE_DATE + ','  + DESC
    json = '{ "transactionId" : "' + TXN_ID + '",' \
           ' "amount" : "' + AMOUNT +'",' \
           ' "acctNumber" : "' + ACCT_IBAN +'",' \
           ' "valueDate" : "' + VALUE_DATE + '",' \
           ' "description" : "' + DESC + '" }'\

    return json


def all_data():
    start = '['
    row = ''
    for x in range(0, 10):
        row = row +  data_row(x) + ','

    row = row[:-1]
    end = ']'
    all_data = start + row + end
    print(all_data)



print('#############################')
all_data()




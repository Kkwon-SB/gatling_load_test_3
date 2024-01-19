from LoadGenerator.Receive.flask import Flask, request

app = Flask(__name__)

@app.route('/starducks/user_order', methods=['POST'])
def post_request():

    print(request)

    return "aa"
if __name__ == '__main__':
    app.run(debug=True)

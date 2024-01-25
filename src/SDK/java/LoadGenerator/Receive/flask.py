#vscode IDE 실행

from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route('/postback/event', methods=['POST'])
def receive_event():
    data = request.get_json()
    print("\nㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ\nReceived event:\n\n", data)
    return jsonify({"status": "success"})


if __name__ == '__main__':
    app.run(port=7955, debug=True)
from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route('/process', methods=['POST'])
def process_data():
    try:
        # Receive JSON data from Spring Boot
        data = request.get_json()
        message = data.get("message")  # Extract message from request

        # Modify the received data (e.g., make it uppercase)
        modified_message = f"My Name is {message}"

        print(f"Received from Java: {message}")  # Print original message
        print(f"Sending back: {modified_message}")  # Print modified message

        # Return modified data as JSON
        return jsonify({"modifiedMessage": modified_message}), 200
    except Exception as e:
        return jsonify({"error": str(e)}), 400

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)  # Flask runs on port 5000

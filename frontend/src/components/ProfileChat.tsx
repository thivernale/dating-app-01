import { SendHorizonal } from 'lucide-react';
import { useState } from 'react';

export function ProfileChat() {
  const [message, setMessage] = useState('');

  function handleSendMessage(): void {
    setMessage('');
    throw new Error('Function not implemented.');
  }

  return (
    <div className="rounded-lg p-4 shadow-lg">
      <h2 className="mb-4 text-2xl font-bold">Chat with Lorem Ipsum</h2>
      <div className="mb-4 h-[50vh] overflow-y-auto rounded-lg border border-gray-200 p-4">
        {['Message', 'Message'].map((message, i) => (
          <div key={i} className="mb-2">
            <div className={`mb-4 p-4 ${i % 2 ? 'bg-gray-100' : ''} `}>
              {message}
            </div>
          </div>
        ))}
      </div>
      <div className="flex place-items-start">
        <textarea
          name=""
          rows={2}
          className="none mr-2 flex-1 rounded-lg border border-gray-100 p-2 outline-none"
          placeholder="Type a message..."
          value={message}
          onChange={(e) => setMessage(e.target.value)}
        ></textarea>
        <button
          className="cursor-pointer rounded-lg bg-blue-600 p-2 text-white"
          onClick={handleSendMessage}
        >
          <SendHorizonal />
        </button>
      </div>
    </div>
  );
}

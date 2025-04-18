import { SendHorizonal, User } from 'lucide-react';
import { useEffect, useState } from 'react';
import { Message, Profile } from '../generated';
import { datingApi } from '../api/dating-api';
import { env } from '../config/env';

interface ProfileChatProps {
  profile: Profile;
  conversationId: string;
}

export function ProfileChat({ profile, conversationId }: ProfileChatProps) {
  const imagesBaseUrl = env.VITE_IMAGES_BASE_URL;
  const [message, setMessage] = useState('');
  const [messages, setMessages] = useState<Message[]>([]);
  const characterId = '-1'; //TODO

  useEffect(() => {
    datingApi.conversationController
      .getConversation({ conversationId })
      .then(({ messages }) => setMessages(messages ?? []));
  }, [conversationId]);

  function handleSendMessage(): void {
    datingApi.conversationController
      .addMessageToConversation({
        conversationId,
        message: { text: message, authorId: characterId, sentAt: new Date() },
      })
      .then(({ messages }) => {
        setMessage('');
        setMessages(messages ?? []);
      });
  }

  return (
    <div className="rounded-lg p-4 shadow-lg">
      <h2 className="mb-4 text-2xl font-bold">
        Chat with {profile.firstName} {profile.lastName}, {profile.age}
      </h2>
      <div className="mb-4 flex flex-wrap text-sm">{profile.bio}</div>
      <div className="mb-4 h-[50vh] overflow-y-auto rounded-lg border border-gray-200 p-4">
        {messages.map((message, i) => (
          <div key={i} className="mb-2">
            <div className="mb-4">
              <div
                className={`flex items-start gap-2 ${message.authorId !== characterId ? 'flex-row-reverse' : 'flex-row'}`}
              >
                {message.authorId !== characterId ? (
                  <img
                    src={imagesBaseUrl + profile.imageUrl}
                    alt=""
                    className="h-12 w-12 rounded-full object-cover"
                  />
                ) : (
                  <div className="flex h-12 w-12 flex-none items-center justify-center rounded-full bg-gray-100 text-3xl dark:bg-gray-800">
                    <User size={16} />
                  </div>
                )}
                <div
                  className={`rounded-2xl p-2 ${message.authorId !== characterId ? 'bg-gray-100 dark:bg-gray-800' : 'bg-blue-100 dark:bg-blue-800'} `}
                >
                  <p className="mb-2 text-xs">
                    {message.sentAt!.toLocaleString()}
                  </p>
                  {message.text}
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>
      <div className="flex place-items-start">
        <textarea
          name=""
          rows={2}
          className="mr-2 flex-1 rounded-lg border border-gray-200 p-2 outline-none"
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

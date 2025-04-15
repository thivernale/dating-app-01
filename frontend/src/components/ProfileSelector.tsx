import { Heart, X } from 'lucide-react';

export function ProfileSelector() {
  return (
    <div className="overflow-hidden rounded-lg shadow-lg">
      <div className="relative">
        <img
          src="/1dee7059-ee63-41d9-813a-b40deedd88dc.jpg"
          alt=""
          style={{ width: '100%', height: '100%' }}
        />
        <div className="absolute right-0 bottom-0 left-0 bg-gradient-to-t from-black p-4">
          <h2 className="text-3xl font-bold text-white">Profile Selector</h2>
        </div>
      </div>
      <div className="p-4">
        <p className="mb-4 text-gray-600">Lorem ipsum </p>
      </div>
      <div className="mb-4 flex justify-center gap-4 p-4">
        <button
          className="cursor-pointer rounded-full bg-red-500 p-4 hover:bg-red-600"
          onClick={() => {}}
        >
          <X size={24} className="text-3xl font-extrabold text-white" />
        </button>
        <button
          className="cursor-pointer rounded-full bg-green-500 p-4 hover:bg-green-600"
          onClick={() => {}}
        >
          <Heart size={24} className="text-3xl font-extrabold text-white" />
        </button>
      </div>
    </div>
  );
}

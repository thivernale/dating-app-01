import { Heart, X } from 'lucide-react';
import { Profile } from '../generated';
import { env } from '../config/env';

type Props = {
  profile?: Profile;
  onSwipe: (arg0: string) => void;
};

export function ProfileSelector({ profile, onSwipe }: Props) {
  const imagesBaseUrl = env.VITE_IMAGES_BASE_URL;

  if (!profile) return <div>Loading...</div>;

  return (
    <div className="overflow-hidden rounded-lg shadow-lg">
      <div className="relative">
        <img
          src={imagesBaseUrl + profile.imageUrl}
          alt=""
          style={{ width: '100%', height: '100%' }}
        />
        <div className="absolute right-0 bottom-0 left-0 bg-gradient-to-t from-black p-4">
          <h2 className="text-3xl font-bold text-white">
            {profile.firstName} {profile.lastName}, {profile.age}
          </h2>
        </div>
      </div>
      <div className="p-4">
        <p className="mb-4 text-gray-600">{profile.bio}</p>
      </div>
      <div className="mb-4 flex justify-center gap-4 p-4">
        <button
          className="cursor-pointer rounded-full bg-red-500 p-4 hover:bg-red-600"
          onClick={() => onSwipe('left')}
        >
          <X size={24} className="text-3xl font-extrabold text-white" />
        </button>
        <button
          className="cursor-pointer rounded-full bg-green-500 p-4 hover:bg-green-600"
          onClick={() => onSwipe('right')}
        >
          <Heart size={24} className="text-3xl font-extrabold text-white" />
        </button>
      </div>
    </div>
  );
}

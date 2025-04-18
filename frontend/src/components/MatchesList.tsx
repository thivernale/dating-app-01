import { useEffect, useState } from 'react';
import { env } from '../config/env';
import { datingApi } from '../api/dating-api';
import { Match } from '../generated';

type Props = {
  onSelectMatch: (match: Match) => void;
};

export function MatchesList({ onSelectMatch }: Props) {
  const imagesBaseUrl = env.VITE_IMAGES_BASE_URL;
  const [matches, setMatches] = useState<Match[]>([]);

  useEffect(() => {
    datingApi.matchController
      .getAllMatches()
      .then((matches) => setMatches(matches));
  }, []);

  return (
    <div className="rounded-lg p-4 shadow-lg">
      <h2 className="mb-4 text-3xl font-bold">Matches</h2>
      <ul>
        {matches.map((match) => (
          <li key={match.id} className="mb-2">
            <button
              className="flex w-full cursor-pointer items-center gap-4 rounded-lg hover:bg-gray-100 dark:hover:bg-gray-800"
              onClick={() => onSelectMatch(match)}
            >
              <img
                src={imagesBaseUrl + match.profile?.imageUrl}
                alt=""
                className="h-16 w-16 rounded-full object-cover"
              />
              <span>
                <h3 className="font-bold">
                  {`${match.profile?.firstName} ${match.profile?.lastName}, ${match.profile?.age}`}
                </h3>
              </span>
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}

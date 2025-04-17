import { env } from '../config/env';

type Props = {
  onSelectMatch: () => void;
};

export function MatchesList({ onSelectMatch }: Props) {
  const imagesBaseUrl = env.VITE_IMAGES_BASE_URL;

  return (
    <div className="rounded-lg p-4 shadow-lg">
      <h2 className="mb-4 text-3xl font-bold">Matches</h2>
      <ul>
        {[{}, {}].map((_, i) => (
          <li key={i} className="mb-2">
            <button
              className="flex w-full cursor-pointer items-center gap-4 rounded-lg hover:bg-gray-100 dark:hover:bg-gray-800"
              onClick={() => onSelectMatch()}
            >
              <img
                src={imagesBaseUrl + '1dee7059-ee63-41d9-813a-b40deedd88dc.jpg'}
                alt=""
                className="h-16 w-16 rounded-full object-cover"
              />
              <span>
                <h3 className="font-bold">match {i}</h3>
              </span>
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}

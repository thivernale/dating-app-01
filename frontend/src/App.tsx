import { MessageCircle, User } from 'lucide-react';
import { ProfileSelector } from './components/ProfileSelector';
import { MatchesList } from './components/MatchesList';

function App() {
  return (
    <>
      <div className="mx-auto h-screen max-w-md p-4">
        <nav className="mb-4 flex justify-between">
          <User />
          <MessageCircle />
        </nav>
        <ProfileSelector />
        <MatchesList />
      </div>
    </>
  );
}

export default App;

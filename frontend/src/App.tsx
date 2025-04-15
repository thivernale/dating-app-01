import { MessageCircle, User } from 'lucide-react';
import { ProfileSelector } from './components/ProfileSelector';
import { MatchesList } from './components/MatchesList';
import { useState } from 'react';

type ActiveView = 'selector' | 'matches';

function App() {
  const [currentView, setCurrentView] = useState<ActiveView>('selector');

  return (
    <>
      <div className="mx-auto h-screen max-w-md p-4">
        <nav className="mb-4 flex justify-between">
          <User
            onClick={() => setCurrentView('selector')}
            className={`${currentView === 'selector' && 'fill-current'} cursor-pointer`}
          />
          <MessageCircle
            onClick={() => setCurrentView('matches')}
            className={`${currentView === 'matches' && 'fill-current'} cursor-pointer`}
          />
        </nav>
        {currentView === 'selector' ? <ProfileSelector /> : <MatchesList />}
      </div>
    </>
  );
}

export default App;

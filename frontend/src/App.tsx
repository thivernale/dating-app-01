import { MessageCircle, User } from 'lucide-react';
import { useEffect, useState } from 'react';
import { ProfileSelector } from './components/ProfileSelector';
import { MatchesList } from './components/MatchesList';
import { ProfileChat } from './components/ProfileChat';
import { Profile } from './generated';
import { datingApi } from './api/dating-api';

type ActiveView = 'selector' | 'matches' | 'chat';

function App() {
  const [currentView, setCurrentView] = useState<ActiveView>('selector');

  const [profile, setProfile] = useState<Profile>();

  function getRandomProfile() {
    datingApi.profileController
      .getRandomProfile()
      .then((value) => setProfile(value));
  }

  function handleSwipe(direction: string) {
    if (direction === 'right') {
      console.log('TODO save swipe right as match');
    }
    getRandomProfile();
  }

  useEffect(getRandomProfile, []);

  const showCurrentView = () => {
    switch (currentView) {
      case 'matches':
        return <MatchesList onSelectMatch={() => setCurrentView('chat')} />;
      case 'chat':
        return <ProfileChat />;
      case 'selector':
      default:
        return <ProfileSelector profile={profile} onSwipe={handleSwipe} />;
    }
  };

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
        {showCurrentView()}
      </div>
    </>
  );
}

export default App;

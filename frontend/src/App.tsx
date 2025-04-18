import { MessageCircle, User } from 'lucide-react';
import { useEffect, useState } from 'react';
import { ProfileSelector } from './components/ProfileSelector';
import { MatchesList } from './components/MatchesList';
import { ProfileChat } from './components/ProfileChat';
import { Match, Profile } from './generated';
import { datingApi } from './api/dating-api';

type ActiveView = 'selector' | 'matches' | 'chat';

function App() {
  const [currentView, setCurrentView] = useState<ActiveView>('selector');
  const [profile, setProfile] = useState<Profile>({} as Profile);
  const [currentMatch, setCurrentMatch] = useState<Match>({} as Match);

  function getRandomProfile() {
    datingApi.profileController
      .getRandomProfile()
      .then((value) => setProfile(value));
  }

  function saveMatch(profileId: string) {
    datingApi.matchController
      .createMatch({ createMatchRequest: { profileId } })
      .then((match) => {
        console.log(match);
      });
  }

  function handleSwipe(direction: string) {
    getRandomProfile();
    if (direction === 'right') {
      saveMatch(profile?.id as string);
    }
  }

  function handleSelectMatch(match: Match) {
    setCurrentMatch(match);
    setCurrentView('chat');
  }

  useEffect(getRandomProfile, []);

  const showCurrentView = () => {
    switch (currentView) {
      case 'matches':
        return <MatchesList onSelectMatch={handleSelectMatch} />;
      case 'chat':
        return (
          <ProfileChat
            profile={currentMatch.profile!}
            conversationId={currentMatch.conversationId!}
          />
        );
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

import { MessageCircle, User } from 'lucide-react';
import { ProfileSelector } from './ProfileSelector';

function App() {
  return (
    <>
      <div className="mx-auto h-screen max-w-md p-4">
        <nav className="mb-4 flex justify-between">
          <User />
          <MessageCircle />
        </nav>
        <ProfileSelector />
      </div>
    </>
  );
}

export default App;

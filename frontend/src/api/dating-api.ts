import {
  Configuration,
  ConversationApi,
  MatchApi,
  ProfileApi,
} from '../generated';
import { env } from '../config/env';

const configuration: Configuration = new Configuration({
  basePath: env.VITE_API_BASE_URL,
  fetchApi: fetch,
});

export const datingApi = {
  profileController: new ProfileApi(configuration),
  matchController: new MatchApi(configuration),
  conversationController: new ConversationApi(configuration),
};

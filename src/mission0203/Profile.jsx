import { getImageUrl } from './utils.jsx';

function Avatar({ person, size }) {

  const thumbnailSize = size < 90 ? 's' : 'b';

  return (
    <img
      className='avatar'
      src={getImageUrl(person.imageId, thumbnailSize)}
      alt={person.name}
      width={size}
      height={size}
      />
  );
}

export default function Profile({ person, imageSize = 100}) {

  return (
    <section className="profile">
      <h2>{person.name}</h2>
      <Avatar person={person} size={imageSize} />
      <ul>
        <li>
          <b>Profession: </b>
          {person.profession}
        </li>
        <li>
          <b>Awards: {person.awards.length} </b>
          {person.awards.join(', ')}
        </li>
        <li>
          <b>Discovered: </b>
          {person.discovered}
        </li>
      </ul>
    </section>
  );
}
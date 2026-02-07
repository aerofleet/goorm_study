const poem = {
  lines: [
    'I write, erase, rewrite',
    'Erase again, and then',
    'A poppy blooms.'
  ]
};

export default function Poem() {
  return (
    <article style={{ textAlign: 'center', padding: '20px' }}>
      {poem.lines.map((line, index) => (
        <div key={index}>
          <p>{line}</p>
          {index === 1 && <hr />}     
        </div>         
      ))}
    </article>
  );
}